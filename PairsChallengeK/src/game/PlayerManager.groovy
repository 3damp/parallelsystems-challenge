package game

import org.jcsp.awt.*
import org.jcsp.groovy.*
import org.jcsp.lang.*
import java.awt.*
import java.awt.Color.*
import org.jcsp.net2.*;
import org.jcsp.net2.tcpip.*;
import org.jcsp.net2.mobile.*;
import java.awt.event.*

class PlayerManager implements CSProcess {
	DisplayList dList
	ChannelOutputList playerNames
	ChannelOutputList pairsWon
	ChannelOutput IPlabel
	ChannelInput IPfield
	ChannelOutput IPconfig
	ChannelInput withdrawButton
	ChannelInput nextButton
	ChannelOutput getValidPoint
	ChannelInput validPoint
	ChannelOutput nextPairConfig
	
	int maxPlayers = 8
	int side = 50
	int minPairs = 3
	int maxPairs = 6
	int boardSize = 6
	
	void run(){
		
		int gap = 5
		def offset = [gap, gap]
		int graphicsPos = (side / 2)
		def rectSize = ((side+gap) *boardSize) + gap

		def displaySize = 4 + (5 * boardSize * boardSize)
		GraphicsCommand[] display = new GraphicsCommand[displaySize]
		GraphicsCommand[] changeGraphics = new GraphicsCommand[5]
		changeGraphics[0] = new GraphicsCommand.SetColor(Color.WHITE)
		changeGraphics[1] = new GraphicsCommand.FillRect(0, 0, 0, 0)
		changeGraphics[2] = new GraphicsCommand.SetColor(Color.BLACK)
		changeGraphics[3] = new GraphicsCommand.DrawRect(0, 0, 0, 0)
		changeGraphics[4] = new GraphicsCommand.DrawString("   ",graphicsPos,graphicsPos)

		def createBoard = {
			display[0] = new GraphicsCommand.SetColor(Color.WHITE)
			display[1] = new GraphicsCommand.FillRect(0, 0, rectSize, rectSize)
			display[2] = new GraphicsCommand.SetColor(Color.BLACK)
			display[3] = new GraphicsCommand.DrawRect(0, 0, rectSize, rectSize)
			def cg = 4
			for ( x in 0..(boardSize-1)){
				for ( y in 0..(boardSize-1)){
					def int xPos = offset[0]+(gap*x)+ (side*x)
					def int yPos = offset[1]+(gap*y)+ (side*y)
					//print " $x, $y, $xPos, $yPos, $cg, "
					display[cg] = new GraphicsCommand.SetColor(Color.WHITE)
					cg = cg+1
					display[cg] = new GraphicsCommand.FillRect(xPos, yPos, side, side)
					cg = cg+1
					display[cg] = new GraphicsCommand.SetColor(Color.BLACK)				
					cg = cg+1
					display[cg] = new GraphicsCommand.DrawRect(xPos, yPos, side, side)				
					cg = cg+1
					xPos = xPos + graphicsPos
					yPos = yPos + graphicsPos
					display[cg] = new GraphicsCommand.DrawString("   ",xPos, yPos)
					//println "$cg"		
					cg = cg+1
				}
			}			
		} // end createBoard
		
		def pairLocations = []
		def colours = [Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.PINK]
		
		def changePairs = {x, y, colour, p ->
			def int xPos = offset[0]+(gap*x)+ (side*x)
			def int yPos = offset[1]+(gap*y)+ (side*y)
			changeGraphics[0] = new GraphicsCommand.SetColor(colour)
			changeGraphics[1] = new GraphicsCommand.FillRect(xPos, yPos, side, side)
			changeGraphics[2] = new GraphicsCommand.SetColor(Color.BLACK)
			changeGraphics[3] = new GraphicsCommand.DrawRect(xPos, yPos, side, side)
			xPos = xPos + graphicsPos
			yPos = yPos + graphicsPos
			if ( p >= 0)
				changeGraphics[4] = new GraphicsCommand.DrawString("   " + p, xPos, yPos)
			else
				changeGraphics[4] = new GraphicsCommand.DrawString(" ??", xPos, yPos)
			dList.change(changeGraphics, 4 + (x*5*boardSize) + (y*5))
		}
	
		def pairsMatch = {pairsMap, cp ->
			// cp is a list comprising two elements each of which is a list with the [x,y]
			// location of a square
			// returns 0 if only one square has been chosen so far
			//         1 if the two chosen squares have the same value (and colour)
			//         2 if the chosen squares have different values
			if (cp[1] == null) return 0
			else {
				if (cp[0] != cp[1]) {
					def p1Data = pairsMap.get(cp[0])
					def p2Data = pairsMap.get(cp[1])
					if (p1Data[0] == p2Data[0]) return 1 else return 2
				}
				else  return 2
			}
		}
		def innerAlt = new ALT([nextButton, withdrawButton])
		def NEXT = 0
		def VALIDPOINT = 0
		def WITHDRAW = 1
		def UPDATE = 2
		createBoard()
		dList.set(display)
		IPlabel.write("What is your name?")
		def playerName = IPfield.read()
		IPconfig.write(" ")
		IPlabel.write("What is the IP address of the game controller?")
		def controllerIP = IPfield.read().trim()
		IPconfig.write(" ")
		IPlabel.write("Connecting to the GameController")
		
		// create Node and Net Channel Addresses
		def nodeAddr = new TCPIPNodeAddress (4000)
		Node.getInstance().init (nodeAddr)
		def toControllerAddr = new TCPIPNodeAddress ( controllerIP, 3000)
		def toController = NetChannel.any2net(toControllerAddr, 50 )
		def fromController = NetChannel.net2one()
		def fromControllerLoc = fromController.getLocation()
		
		// connect to game controller
		IPconfig.write("Now Connected - sending your name to Controller")
		def enrolPlayer = new EnrolPlayer( name: playerName,
										   toPlayerChannelLocation: fromControllerLoc)
		toController.write(enrolPlayer)
		def enrolDetails = (EnrolDetails)fromController.read()
		def myPlayerId = enrolDetails.id
		def enroled = true
		def unclaimedPairs = 0
		if (myPlayerId == -1) {
			enroled = false
			IPlabel.write("Sorry " + playerName + ", there are too many players enroled in this PAIRS game")
			IPconfig.write("  Please close the game window")
		}
		else {
			IPlabel.write("Hi " + playerName + ", you are now enroled in the PAIRS game")
			IPconfig.write(" ")	
			def outerAlt = new ALT([validPoint, withdrawButton, fromController])
			
			
			//================== MAIN LOOP ======================
			while (enroled) {
				toController.write(new GetGameDetails(id: myPlayerId))
				def gameDetails = (GameDetails)fromController.read()
				
				createBoard()
				def chosenPairs = gameDetails.currentDisplay
				
				dList.change (display, 0)
				def isPlaying = gameDetails.currentPlayer == myPlayerId
				def gameId = gameDetails.gameId
				def playerMap = gameDetails.playerDetails
				def pairsMap = gameDetails.pairsSpecification
				def playerIds = playerMap.keySet()
				for (p in 0..4){
					def pData = playerMap.get(p)
					playerNames[p].write(pData != null ? pData[0] : "Empty slot")
					pairsWon[p].write(pData != null ? " " + pData[1] : "   ")
				}
				
				IPconfig.write("Playing Game Number #" + gameId + " - " + (isPlaying ? "It's your turn" : playerMap.get(gameDetails.currentPlayer)[0] + " is playing"))	
				
				
				// now use pairsMap to create the board
				def pairLocs = pairsMap.keySet()
				pairLocs.each {loc ->
					changePairs(loc[0], loc[1], Color.LIGHT_GRAY, -1)
				}
				
				def currentPair = 0
				
				// If the player has already return a card, we display it and update currentPair
				if (chosenPairs[0] != null){					
					def pairData = pairsMap.get(chosenPairs[0])					
					changePairs(chosenPairs[0][0], chosenPairs[0][1], pairData[1], pairData[0])
					currentPair = 1
				}
				def notMatched = true
				while ((chosenPairs[1] == null) && (enroled)) {
					if (isPlaying)
						getValidPoint.write (new GetValidPoint( side: side,
															gap: gap,
															pairsMap: pairsMap))
					def preCond = new boolean[3]
					preCond[WITHDRAW] = true
					preCond[VALIDPOINT] = isPlaying
					preCond[UPDATE] = true			
					println "Before select..."
					switch ( outerAlt.priSelect(preCond) ) {
						case WITHDRAW:	
							withdrawButton.read()
							toController.write(new WithdrawFromGame(id: myPlayerId))
							enroled = false
							break						
						case VALIDPOINT:
							def vPoint = ((SquareCoords)validPoint.read()).location
							println "Card selected: $vPoint"
							chosenPairs[currentPair] = vPoint
							
							toController.write(new ClaimCard ( id: myPlayerId,
											   	   			   gameId: gameId,
															   card: vPoint))
							
							currentPair = currentPair + 1
							def pairData = pairsMap.get(vPoint)
							
							changePairs(vPoint[0], vPoint[1], pairData[1], pairData[0])
							break
						case UPDATE:
							def o = fromController.read()
							if (o instanceof ClaimCard){	
								//the current player flip a card
								
								def vPoint = o.card							
								def pairData = pairsMap.get(vPoint)		
								
								chosenPairs[currentPair] = vPoint		
								println "receive pair ${currentPair}"						
								currentPair = currentPair + 1
														
								changePairs(vPoint[0], vPoint[1], pairData[1], pairData[0])
							} else if (o == -1)
								//the current player withdraw
								chosenPairs = [true, true]
							break							
					}// end of outer switch	
				} // end of while getting two pairs
			} // end of while enrolled loop
			IPlabel.write("Goodbye " + playerName + ", please close game window")
		} //end of enrolling test
	} // end run
}				

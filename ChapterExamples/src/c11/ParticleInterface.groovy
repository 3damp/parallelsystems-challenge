package c11
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.groovy.*
import org.jcsp.lang.*
import phw.util.*
import org.jcsp.util.*
import org.jcsp.awt.*

class ParticleInterface implements CSProcess {
	
  def ChannelInput inChannel
  def ChannelOutput outChannel
  def int canvasSize = 100
  def int particles
  def int centre
  def int initialTemp
  
  void run() {
    def dList = new DisplayList()
    def particleCanvas = new ActiveCanvas()
    particleCanvas.setPaintable (dList)
    def tempConfig = Channel.one2one()
    def pauseConfig = Channel.one2one()
    def uiEvents = Channel.any2one( new OverWriteOldestBuffer(5) )
    def network = [ new ParticleManager ( fromParticles: inChannel, 
                                          toParticles: outChannel,
                                          toUI: dList,
                                          fromUIButtons: uiEvents.in(),
                                          toUIPause: pauseConfig.out(),
                                          toUILabel: tempConfig.out(),
                                          CANVASSIZE: canvasSize,
                                          PARTICLES: particles,
                                          CENTRE: centre,
                                          START_TEMP: initialTemp ),
                    new UserInterface   ( particleCanvas: particleCanvas, 
                                          canvasSize: canvasSize,
                                          tempValueConfig: tempConfig.in(),
                                          pauseButtonConfig: pauseConfig.in(),
                                          buttonEvent: uiEvents.out()  )
                  ]
    new PAR ( network ).run()
  }
}
   
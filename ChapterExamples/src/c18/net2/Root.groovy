package c18.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Root implements CSProcess{
  
  def ChannelInput inChannel
  def ChannelOutput outChannel
  def int iterations
  def String initialValue  

  void run() {
    def N2A = Channel.one2one()
    def A2N = Channel.one2one()  
    def ChannelInput toAgentInEnd = N2A.in()
    def ChannelInput fromAgentInEnd = A2N.in()
    def ChannelOutput toAgentOutEnd = N2A.out()
    def ChannelOutput fromAgentOutEnd = A2N.out()

    def theAgent = new Agent( results: [initialValue])
    
    for ( i in 1 .. iterations) {
      outChannel.write(theAgent)
      theAgent = inChannel.read()
      theAgent.connect ( [fromAgentOutEnd, toAgentInEnd ] )
      def agentManager = new ProcessManager (theAgent)
      agentManager.start()
      def returnedResults = fromAgentInEnd.read()
      println "Root: Iteration: $i is $returnedResults "    
      returnedResults << "end of " + i
      toAgentOutEnd.write (returnedResults)
      agentManager.join()
      theAgent.disconnect()
    }
  }
}

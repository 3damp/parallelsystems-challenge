package c18.net2

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.net2.*
import org.jcsp.lang.*
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
 
class ForwardBackAgent implements MobileAgent {
  
  def ChannelOutput toLocal
  def ChannelInput fromLocal
  def NetChannelLocation backChannel
  
  def results = [ ]
                  
  def connect ( c ) {
    this.toLocal = c[0]
    this.fromLocal = c[1]
  }
  
  def disconnect (){
    toLocal = null
    fromLocal = null
  }

  void run() {
    def fromRoot = NetChannel.net2one()
    def fromRootLocation = fromRoot.getLocation()
    def toRoot = NetChannel.one2net (backChannel)
    toRoot.write(fromRootLocation)
    results << fromRoot.read()
    toLocal.write (results)
    results = fromLocal.read()
    def last = results.size - 1
    toRoot.write(results[last])
  }

}
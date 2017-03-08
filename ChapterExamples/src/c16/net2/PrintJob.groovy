package c16.net2
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.net2.*

class PrintJob  implements Serializable{
	
  def int userId
  def NetChannelLocation useLocation
}

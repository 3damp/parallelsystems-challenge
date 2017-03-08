package c05;
   
    
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel




class ScaledData implements Serializable {
	
  def int original
  def int scaled 
   
  def String toString () {
	  def s = " " + original + "\t\t" + scaled
	  return s 
  }	
}

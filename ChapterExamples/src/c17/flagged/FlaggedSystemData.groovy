package c17.flagged

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel





class FlaggedSystemData extends SystemData {  
	
  def boolean testFlag = false
  
  def String toString() {
    String s
    s = "Flagged System Data: [" + a + ", " + b + ", " + c + ", " + testFlag + "]"
    return s
  }
}

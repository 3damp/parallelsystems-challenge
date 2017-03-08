package c17.counted

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel





class CountedData {
	
  def counter
  def value  
  
  def String toString () {
    def s = "Counted Data: [ " + counter + ", " + value + " ]"
    return s
  }
}

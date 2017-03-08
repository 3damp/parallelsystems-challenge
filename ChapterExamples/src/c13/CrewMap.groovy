package c13
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*


class CrewMap extends  HashMap<Object, Object> {
	
  def theCrew = new Crew()
  
  def Object put ( Object itsKey, Object itsValue ) {
    theCrew.startWrite()
    super.put ( itsKey, itsValue )
    theCrew.endWrite()
  }
  
  def Object get ( Object itsKey ) {
    theCrew.startRead()
    def result = super.get ( itsKey )
    theCrew.endRead()
    return result
  }

}

  
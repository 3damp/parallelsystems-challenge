package c14
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class TargetFlusher implements CSProcess {
	
  def buckets
  def ChannelOutput targetsFlushed
  def ChannelInput flushNextBucket
  def Barrier initBarrier

  void run() {
    def nBuckets = buckets.size()
    def currentBucket = 0
    def targetsInBucket = 0
    while (true) {
      flushNextBucket.read()
      targetsInBucket = buckets[currentBucket].holding()
      while ( targetsInBucket == 0) {
        currentBucket = (currentBucket + 1) % nBuckets
        targetsInBucket = buckets[currentBucket].holding()        
      } // end of while targetsInBucket
      initBarrier.reset( targetsInBucket)
      targetsFlushed.write(targetsInBucket)
      buckets[currentBucket].flush()
      currentBucket = (currentBucket + 1) % nBuckets
    } // end of while true
  }
}

package c17.counted 
  
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*


def a = Channel.one2one()
def b = Channel.one2one()
def c = Channel.one2one()
def d = Channel.one2one()
def e = Channel.one2one()
def f = Channel.one2one()
def g = Channel.one2one()
def h = Channel.one2one()

def dataGen = new GNumbers ( outChannel: a.out() )

def sampler = new CountingSampler ( inChannel: a.in(), 
                                     outChannel: b.out(), 
                                     sampleRequest: e.in(),
                                     countReturn: g.out() )

def samplingTimer = new CountedSamplingTimer ( sampleRequest: e.out(), 
                                                sampleInterval: 2500,
                                                countReturn: g.in(),
                                                countToGatherer: h.out() )

def sampledNetwork = new CountedSampledNetwork ( inChannel: b.in(),
                                                  outChannel: c.out() )

def gatherer = new CountingGatherer ( inChannel: c.in(),
                                       outChannel: d.out(),
                                       gatheredData: f.out(),
                                       countInput: h.in() )

def evaluator = new CountedEvaluator (inChannel: f.in())
 
def printResults = new GPrint ( inChannel: d.in(),
                                 heading: "output Values",
                                 delay: 0)

def network = [dataGen, sampler, samplingTimer, sampledNetwork, 
               gatherer, evaluator, printResults ]    
                       
new PAR(network).run()               


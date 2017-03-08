package c23.loaderObjects

import org.jcsp.lang.*






interface WorkerInterface extends CSProcess, Serializable{

	abstract connect(inChannels, outChannels)  
}

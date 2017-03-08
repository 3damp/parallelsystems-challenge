package c24.Distributed.loaderObjects








class RequestWorker implements Serializable {
	
	def loadLocation //net channel input location used to read WorkerObject
	def nodeIP		 // Ip address of the worker node sending object to host
}

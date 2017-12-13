//beacon: UUID, Broadcast iBeacon packets every 100ms
//gateways: Scan for iBeacon packets every 100ms. Post Scan Data (Gateway UUID, Beacon UUID, RSSI: -40dbm to -100dbm, Timestamp) to Cloud
//cloud: Receive scan data, send data to subscribers

e.g:
[{"gatewayUUID":"restroom", "beaconUUID":"jack", "rssi":-40, "timestamp": 1506101825},
{"gatewayUUID":"library", "beaconUUID":"jack", "rssi":-40, "timestamp":1506104822}, 
{"gatewayUUID":"restroom", "beaconUUID":"jack", "rssi":-100, "timestamp":1506104825}...]	//Delta under 200ms

//output: {"jack": "library"}

[{"gatewayUUID":"library", "beaconUUID":"jack", "rssi":-40, "timestamp":1506105822}, 
{"gatewayUUID":"restroom", "beaconUUID":"jack", "rssi":-100,  "timestamp":1506105825}...]

//output: {"jack": "library"}

[{"gatewayUUID":"library", "beaconUUID":"jack", "rssi":-50, "timestamp":1506106925}]

//output: {} or undefined

[{"gatewayUUID":"restroom", "beaconUUID":"jack", "rssi":-40, "timestamp":1506106825}]

//output: {"jack": "restroom"}

[{"gatewayUUID":"library", "timestamp":1506108822}, 
{"gatewayUUID":"restroom", "beaconUUID":"jack", "rssi":-40,  "timestamp":1506108825}...]

//output: {"jack": "restroom"}

// Gateways
gateways = ["library", "restroom", "3", "4"]


//Callback from server
receiveScans(scanData) {
	plotPeople(locatePeople(scanData))
}

plotPeople()

//Return map of people, location 
//List<DataSet> scanData
//HashMap<String,String> dataSet

//PriorityQueue<"dataSet"> recentTimes

locatePeople(scanData) {
	//PriorityQueue<> recentTimes = new PriorityQueue(
  
  DataSet recentDataSet
  List recentGateways = gateways;
  HashMap<String,List<DataSet>> uuidSets;
  
		for (DataSet data : scanData) {
    	if (Math.abs(data.timestamp - recentDataSet.timestamp) < 200) {
      	if (data.beaconUUID.equals(recentDataSet.beaconUUID)) {
        	if (recentGateways.remove(data.gateway)) {
          	
          }
          if (recentGateways.isEmpty()) {
          	//compare rssi
            //reset recentGateways
          }
        }
        recentDataSet = data;
      }
      recentDataSet = Math.max(recentDataSet.timestamp, data.timestamp) ? recentDataSet : data;
    }
        
  	
}

Class DataSet {
	String gateway;
  String beaconUUID;

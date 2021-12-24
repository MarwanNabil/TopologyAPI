# TopologyAPI
Provide the functionality to access, manage and store device topologies.


Requirments are (describing the project)
![First Page](https://github.com/MarwanNabil/TopologyAPI/raw/main/images/req1.png)
![Second Page](https://github.com/MarwanNabil/TopologyAPI/raw/main/images/req2.png)

about the other side:

I made two classes handling all the work.
backend-class which called "TopologyCore"
API-class which called "TopologyAPI"

the third class we can call it a client (assuming the Server-Client Service handled by API as midway slave)

Almost Everything about TopologyCore:
    it handles everything related to a topology, once you have created an instance of topolgy , you must pass JSON file to the constructor.
    takes this json file parses it (getting topology-id, netlists and connected devices to it.

what about the mid-way? "TopologyAPI"
    as we know api serves clients sending data via JSON files.
    and this is the purpose of that class.
    

poor client class
    just we treat it as named "Client" we have a json file which is already uploaded to the requirment.pdf
    we use it as starting a connection.
    
    
leave the results with you
![Client Code](https://github.com/MarwanNabil/TopologyAPI/raw/main/images/out1.png)
![Output](https://github.com/MarwanNabil/TopologyAPI/raw/main/images/out1.png)

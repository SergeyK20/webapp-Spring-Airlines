var app = angular.module("FlightsController",[])

app.controller("FlightsCtrl", function($scope,$http){
    $scope.flights=[];
    $scope.flightsForm={
        idFlight: 1,
        numFlight:"",
        airportsDeparture:"2",
        airportsArrival:"2",
        departureDate:"2",
        departureTime:"",
        aircraftName:""
    };
    -_refreshFlightsData()
    function _refreshFlightsData() {
        $http({
            method: 'GET',
            url: '/flights'
        }).then(
            function (res) { // success
                $scope.flights = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
    $scope.submitFlight = function() {
        var method="";
        var url="";
            if($scope.flightsForm.idFlight ==-1){
                method("POST")
                url='/flights'
            }else {
                method = "PUT";
                url = '/flights';
            }
            $http({
                method: method,
                url: url,
                data: angular.toJson($scope.flightsForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
    };

    $scope.createFlight = function() {
        _clearFormData();
    }
    $scope.deleteFlight = function(flight) {
        $http({
            method: 'DELETE',
            url: '/flights/' + flight.idFlight
        })
    };
    $scope.editFlight = function(flight) {
        $scope.flightsForm.idFlight= flight.idFlight;
        $scope.flightsForm.numFlight = flight.numFlight;
        $scope.flightsForm.airportsDeparture =flight.airportsDeparture;
        $scope.flightsForm.airportsArrival =flight.airportsArrival;
        $scope.flightsForm.aircraftName= flight.aircraftName;
        $scope.flightsForm.departureTime= flight.departureTime;
        $scope.flightsForm.departureDate = flight.departureDate;

    };


})
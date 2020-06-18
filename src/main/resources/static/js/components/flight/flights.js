var app = angular.module("FlightsController", [])

app.controller("FlightsCtrl", function ($scope, $http,FlightService) {
    $scope.flights = [];
    let airport1;
    let airport2;
    let aircraft;
    $scope.cities=[];
    $scope.aircrafts=[];
    $scope.aircraft={
        id:1,
        nameAircraft:"Tu144",
        numberSeatsAircraft:44
    }
    $scope.airport=[];
    let city;
    $scope.airport={
        id: 1,
        nameAirport:"Kurumoch",
        airportInTheCity:city
    };
    $scope.flight = {
        idFlight: "1",
        numFlight: "s12324",
        airportDeparture: airport1,
        airportArrival: airport2,
        departureDate: "03.02.2020",
        departureTime: "17:50",
        aircraft: aircraft,
        price: "5750"
    };
    FlightsData()
    AirportsData()
    CityData()
    AircraftData()
    function AircraftData() {
        $http({
            method: 'GET',
            url: '/aircraft'
        }).then(
            function (res) { // success
                $scope.aircrafts = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
    function CityData() {
        $http({
            method: 'GET',
            url: '/city'
        }).then(
            function (res) { // success
                $scope.cities = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
    function AirportsData() {
        $http({
            method: 'GET',
            url: '/airports'
        }).then(
            function (res) { // success
                $scope.airports = res.data
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    function FlightsData() {
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
    };$scope.updateFlight = function () {
        AirportService.updateFlight($scope.flight.idFlight,$scope.flight.numFlight, $scope.flight.airportDeparture, $scope.flight.airportArrival, $scope.flight.departureDate, $scope.flight.departureTime, $scope.flight.aircraft, $scope.flight.price)
            .then(FlightsData)
            .then(function success(response){
                    $scope.message = 'Flight data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating Flight!';
                    $scope.message = '';
                });
    }

    $scope.addFlight = function () {
        if ($scope.flight.departureDate != null ) {
            FlightService.addFlight($scope.flight.numFlight, $scope.flight.airportDeparture, $scope.flight.airportArrival, $scope.flight.departureDate, $scope.flight.departureTime, $scope.flight.aircraft, $scope.flight.price)
                .then(FlightsData)
                .then (function success(response){
                        $scope.message = 'Flight added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding Flight!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }

    $scope.deleteFlight = function (index) {
        FlightService.deleteFlight(index)
            .then(FlightsData)
            .then (function success(response){
                    $scope.message = 'Flight deleted!';
                    $scope.flight = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting Flight!';
                    $scope.message='';
                })
    }

})
app.service('FlightService', ['$http', function ($http) {

    this.getFlight = function getFlight(id) {
        return $http({
            method: 'GET',
            url: '/flights/' + id
        });
    }

    this.addFlight = function addFlight(numFlight, airportDeparture, airportArrival, departureDate, departureTime, aircraft, price) {
        return $http({
            method: 'POST',
            url: '/flights',
            data: {
                numFlight: numFlight,
                airportDeparture: airportDeparture,
                airportArrival: airportArrival,
                departureDate: departureDate,
                departureTime: departureTime,
                aircraft: aircraft,
                price: price}
        });
    }

    this.deleteFlight = function deleteFlight(id) {
        return $http({
            method: 'DELETE',
            url: '/flights/' + id
        })
    }

    this.updateFlight = function updateFlight(idFlight, numFlight, airportDeparture, airportArrival, departureDate, departureTime, aircraft, price) {
        return $http({
            method: 'PUT',
            url: '/flights/' + idFlight,
            data: {
                idFlight: idFlight,
                numFlight: numFlight,
                airportDeparture: airportDeparture,
                airportArrival: airportArrival,
                departureDate: departureDate,
                departureTime: departureTime,
                aircraft: aircraft,
                price: price
            }
        })
    }
    this.getFlight = function getFlight(id) {
        return $http({
            method: 'GET',
            url: '/flights/' + id
        });
    }

}]);
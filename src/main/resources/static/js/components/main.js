var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http) {
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
    $scope.flightsRoleUserDto = [];

    $scope.flightRoleUserDto = {
        id: "1",
        numFlight: "s12324",
        airportDeparture: airport1,
        airportArrival: airport2,
        departureDate: "03.02.2020",
        departureTime: "17:50",
        aircraft: aircraft,
        price: "5750"
    };
    MainData()
    function MainData() {
        $http({
            method: 'GET',
            url: '/user_flight/user'
        }).then(
            function (res) { // success
                $scope.flightsRoleUserDto = res.data.flightRoleUserDto;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
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
    };

})
app.service('MainService', ['$http', function ($http) {

    this.getFlight = function getFlight(id) {
        return $http({
            method: 'GET',
            url: '/flights/' + id
        });
    }

    this.addFlight = function addFlight(idFlight,idUser,payment) {
        return $http({
            method: 'POST',
            url: '/booking',
            data: {idUser:idUser,
               id:idFlight,
                payment:payment}
        });
    }

    this.deleteFlight = function deleteFlight(id) {
        return $http({
            method: 'DELETE',
            url: '/flights/' + id
        })
    }

    this.updateFlight = function updateFlight(id, numFlight, airportDeparture, airportArrival, departureDate, departureTime, aircraft, price) {
        return $http({
            method: 'PUT',
            url: '/flights/' + id,
            data: {
                id: id,
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
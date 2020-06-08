var app = angular.module("Airports",[])

app.controller("AirportsCtrl", function($scope,$http,AirportService){
    $scope.airports=[];
    $scope.city={
        id: 1,
        nameCity:"hello"
    };
    city ={id: 1,
        nameCity:"hello"};
    $scope.airport={
        id: 1,
        nameAirport:"Kurumoch",
        airportInTheCity: city
    };
    $scope.cities=[];

    AirportsData()
    _refreshCityData()
    function _refreshCityData() {
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
    $scope.updateAirport = function () {
        AirportService.updateAirport($scope.airport.nameAirport,$scope.airport.airportInTheCity)
            .then(function success(response){
                    $scope.message = 'Airport data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating airport!';
                    $scope.message = '';
                });
        AirportsData();
    }

    $scope.getAirport = function () {
        var id = $scope.airport.id;
        AirportService.getAirport($scope.airport.id)
            .then(function success(response) {
                    $scope.airport = response.data;
                    $scope.airport.id = id;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    if (response.status === 404) {
                        $scope.errorMessage = 'Airport not found!';
                    } else {
                        $scope.errorMessage = "Error getting airport!";
                    }
                });
        AirportsData();
    }

    $scope.addAirport = function () {
        if ($scope.airport != null ) {
            AirportService.addAirport($scope.airport.nameAirport, $scope.airport.airportInTheCity)
                .then (function success(response){
                        $scope.message = 'Airport added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding airport!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
        AirportsData();
    }

    $scope.deleteAirport = function () {
        AirportService.deleteAirport($scope.airport.id)
            .then (function success(response){
                    $scope.message = 'Airport deleted!';
                    $scope.airport = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting airport!';
                    $scope.message='';
                })
        AirportsData();
    }

})
app.service('AirportService',['$http', function ($http) {

    this.getAirport = function getAirport(id){
        return $http({
            method: 'GET',
            url: '/airports/'+id
        });
    }

    this.addAirport = function addAirport(nameAirport,airportinTheCity){
        return $http({
            method: 'POST',
            url: '/airports',
            data: {nameAirport:nameAirport,
                    airportInTheCity:airportinTheCity}
        });
    }

    this.deleteAirport = function deleteAirport(id){
        return $http({
            method: 'DELETE',
            url: '/airports/'+id
        })
    }

    this.updateAirport = function updateAirport(id,nameAirport, airportInTheCity){
        return $http({
            method: 'PUT',
            url: '/airports/'+id,
            data: {nameAirport:nameAirport,
                airportInTheCity:airportInTheCity}
        })
    }
    this.getAirport = function getAirport(id) {
        return $http({
            method: 'GET',
            url: '/airports/' + id
        });
    }

}]);

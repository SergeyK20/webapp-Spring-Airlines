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
        AirportService.updateAirport($scope.airport.id,$scope.airport.nameAirport,$scope.airport.airportInTheCity)
            .then(function success(response){
                    $scope.message = 'Airport data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating airport!';
                    $scope.message = '';
                });
        setTimeout("location.reload(true);",1)
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
        setTimeout("location.reload(true);",1)
        AirportsData();
    }

    $scope.deleteAirport = function (index) {
        AirportService.deleteAirport(index)
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
        setTimeout("location.reload(true);",1)
    };

})
app.service('AirportService',['$http', function ($http) {

    this.getAirport = function getAirport(id){
        return $http({
            method: 'GET',
            url: '/airports/'+id
        });
    }

    this.addAirport = function addAirport(nameAirport,airportInTheCity){
        return $http({
            method: 'POST',
            url: '/airports',
            data: {nameAirport:nameAirport,
                    airportInTheCity:  airportInTheCity}
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
            data: {id:id,
                nameAirport:nameAirport,
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

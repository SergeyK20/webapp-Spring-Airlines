var app = angular.module('aircraft', []);
app.controller("AircraftCtrl", function ($scope, $http, AircraftService) {
    $scope.aircrafts = [];
    $scope.aircraft = {
        id: 1,
        nameAircraft: "Tu144",
        numberSeatsAircraft: 44
    };

    AircraftData()
    $scope.editAircraft = function(aircraft) {
        $scope.aircraft.id = aircraft.id;
        $scope.aircraft.nameAircraft = aircraft.nameAircraft;
        $scope.aircraft.numberSeatsAircraft=aircraft.numberSeatsAircraft;
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
    $scope.updateAircraft = function () {
        AircraftService.updateAircraft($scope.aircraft.id, $scope.aircraft.nameAircraft,$scope.aircraft.numberSeatsAircraft)
            .then(AircraftData)
            .then(function success(response) {
                    $scope.message = 'Aircraft data updated!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error updating aircraft!';
                    $scope.message = '';
                });
    }


    $scope.addAircraft = function () {
        if ($scope.aircraft != null && $scope.aircraft.nameAircraft) {
            AircraftService.addAircraft($scope.aircraft.nameAircraft,$scope.aircraft.numberSeatsAircraft)
                .then(AircraftData)
                .then(function success(response) {
                        $scope.message = 'Aircraft added!';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error adding aircraft!';
                        $scope.message = '';
                    });
        } else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }

    $scope.deleteAircraft = function (index) {
        AircraftService.deleteAircraft(index)
            .then(AircraftData)
            .then(function success(response) {
                    $scope.message = 'Aircraft deleted!';
                    $scope.aircraft = null;
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error deleting Aircraft!';
                    $scope.message = '';
                })
       }


});
app.service('AircraftService', ['$http', function ($http) {

    this.getAircraft = function getAircraft(id) {
        return $http({
            method: 'GET',
            url: '/aircraft/' + id
        });
    }

    this.addAircraft = function addAircraft( nameAircraft, numberSeatsAircraft) {
        return $http({
            method: 'POST',
            url: '/aircraft',
            data: {
            nameAircraft:nameAircraft,
            numberSeatsAircraft:numberSeatsAircraft}
        });
    }

    this.deleteAircraft = function deleteAircraft(id) {
        return $http({
            method: 'DELETE',
            url: '/aircraft/' + id
        })
    }

    this.updateAircraft = function updateAircraft(id, nameAircraft, numberSeatsAircraft) {
        return $http({
            method: 'PUT',
            url: '/aircraft/' + id,
            data: {id:id,
                nameAircraft:nameAircraft,
                numberSeatsAircraft:numberSeatsAircraft}
        })
    }
    this.getAircraft = function getAircraft(id) {
        return $http({
            method: 'GET',
            url: '/aircraft/' + id
        });
    }

}]);

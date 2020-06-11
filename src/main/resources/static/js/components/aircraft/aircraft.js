var app = angular.module('aircraft', []);
app.controller("AircraftCtrl", function ($scope, $http, AircraftService) {
    $scope.aircrafts = [];
    $scope.aircraft = {
        id: 1,
        nameAircraft: "Tu144",
        numberSeatsAircraft: 44
    };

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
    $scope.updateAircraft = function () {
        AircraftService.updateAircraft($scope.aircraft.id, $scope.aircraft.nameAircraft,$scope.aircraft.numberSeatsAircraft)
            .then(function success(response) {
                    $scope.message = 'Aircraft data updated!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error updating aircraft!';
                    $scope.message = '';
                });
        AircraftData();
        setTimeout("location.reload(true);",1)
    }

    $scope.getAircraft = function () {
        var id = $scope.aircraft.id;
        AircraftService.getAircraft($scope.aircraft.id)
            .then(function success(response) {
                    $scope.aircraft = response.data;
                    $scope.aircraft.id = id;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    if (response.status === 404) {
                        $scope.errorMessage = 'Aircraft not found!';
                    } else {
                        $scope.errorMessage = "Error getting aircraft!";
                    }
                });
        AircraftData()
    }

    $scope.addAircraft = function () {
        if ($scope.aircraft != null && $scope.aircraft.nameAircraft) {
            AircraftService.addAircraft($scope.aircraft.nameAircraft,$scope.aircraft.numberSeatsAircraft)
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
        AircraftData();
        setTimeout("location.reload(true);",1)
    }

    $scope.deleteAircraft = function (index) {
        AircraftService.deleteAircraft(index)
            .then(function success(response) {
                    $scope.message = 'Aircraft deleted!';
                    $scope.aircraft = null;
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error deleting Aircraft!';
                    $scope.message = '';
                })
        AircraftData();
        setTimeout("location.reload(true);",1)
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

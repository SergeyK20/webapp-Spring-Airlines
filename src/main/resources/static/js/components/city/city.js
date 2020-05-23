var app = angular.module('app',[]);
app.controller("CityCtrl", function ($scope, $http,CityCRUDService) {
    $scope.cities = [];
    $scope.city={
        idCity: 1,
        nameCity:"hello"
    };

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
    $scope.updateCity = function () {
        CityCRUDService.updateCity($scope.city.idCity,$scope.city.nameCity)
            .then(function success(response){
                    $scope.message = 'City data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating city!';
                    $scope.message = '';
                });
        _refreshCityData();
    }

    $scope.getCity = function () {
        var id = $scope.city.idCity;
        CityCRUDService.getCity($scope.city.idCity)
            .then(function success(response){
                    $scope.city = response.data;
                    $scope.city.idCity = id;
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'City not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting city!";
                    }
                });
        _refreshCityData();
    }

    $scope.addCity = function () {
        if ($scope.city != null && $scope.city.nameCity) {
            CityCRUDService.addCity($scope.city.nameCity)
                .then (function success(response){
                        $scope.message = 'City added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding city!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
        _refreshCityData();
    }

    $scope.deleteCity = function () {
        CityCRUDService.deleteCity($scope.city.idCity)
            .then (function success(response){
                    $scope.message = 'City deleted!';
                    $scope.city = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting city!';
                    $scope.message='';
                })
        _refreshCityData();
    }


});
app.service('CityCRUDService',['$http', function ($http) {

    this.getCity = function getCity(idCity){
        return $http({
            method: 'GET',
            url: '/city/'+idCity
        });
    }

    this.addCity = function addCity(nameCity){
        return $http({
            method: 'POST',
            url: '/city',
            data: {nameCity:nameCity}
        });
    }

    this.deleteCity = function deleteCity(idCity){
        return $http({
            method: 'DELETE',
            url: '/city/'+idCity
        })
    }

    this.updateCity = function updateCity(idCity,nameCity){
        return $http({
            method: 'PATCH',
            url: '/city/'+idCity,
            data: {nameCity:nameCity}
        })
    }

}]);
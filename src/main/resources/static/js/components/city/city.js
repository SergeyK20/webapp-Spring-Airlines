var app = angular.module('app',[]);
app.controller("CityCtrl", function ($scope, $http, CityService) {
    $scope.cities = [];
    $scope.city={
        id: 1,
        nameCity:"hello"
    };

    CityData()
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
    $scope.updateCity = function () {
        CityService.updateCity($scope.city.id,$scope.city.nameCity)
            .then(function success(response){
                    $scope.message = 'City data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating city!';
                    $scope.message = '';
                });
        CityData();
    }

    $scope.getCity = function (id) {
        var id = $scope.city.id;
        CityService.getCity($scope.city.id)
            .then(function success(response) {
                    $scope.city = response.data;
                    $scope.city.id = id;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    if (response.status === 404) {
                        $scope.errorMessage = 'Customer not found!';
                    } else {
                        $scope.errorMessage = "Error getting customer!";
                    }
                });
        CityData();
    }

    $scope.addCity = function () {
        if ($scope.city != null && $scope.city.nameCity) {
            CityService.addCity($scope.city.nameCity)
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
        CityData();
    }

    $scope.deleteCity = function () {
        CityService.deleteCity($scope.city.id)
            .then (function success(response){
                    $scope.message = 'City deleted!';
                    $scope.city = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting city!';
                    $scope.message='';
                })
        CityData();
    }


});
app.service('CityService',['$http', function ($http) {

    this.getCity = function getCity(id){
        return $http({
            method: 'GET',
            url: '/city/'+id
        });
    }

    this.addCity = function addCity(nameCity){
        return $http({
            method: 'POST',
            url: '/city',
            data: {nameCity:nameCity}
        });
    }

    this.deleteCity = function deleteCity(id){
        return $http({
            method: 'DELETE',
            url: '/city/'+id
        })
    }

    this.updateCity = function updateCity(id,nameCity){
        return $http({
            method: 'PUT',
            url: '/city/'+id,
            data: {nameCity:nameCity}
        })
    }
    this.getCity = function getCity(id) {
        return $http({
            method: 'GET',
            url: '/city/' + id
        });
    }

}]);

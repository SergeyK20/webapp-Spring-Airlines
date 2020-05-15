var app = angular.module("CityController", [])

app.controller("CityCtrl", function ($scope, $http) {
    $scope.cities = [];
    $scope.city={
        idCity: 1,
        nameCity:"hello"
    };

    _refreshEmployeeData()
    function _refreshEmployeeData() {
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
    };$scope.submitCity= function() {
        var method="";
        var url="";
        if($scope.city ==-1){
            method("POST")
            url='/city'
        }else {
            method = "PUT";
            url = '/city';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.city),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
    $scope.createCity = function() {
        _clearFormData();
    };
    $scope.deleteCity = function(city) {
        $http({
            method: 'DELETE',
            url: '/city/' + city.idCity
        }).then(_success, _error);
    };
    function _success(res) {
        _refreshEmployeeData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

});
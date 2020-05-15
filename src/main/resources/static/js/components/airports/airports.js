var app = angular.module("AirportsController",[])

app.controller("AirportsCtrl", function($scope,$http){
    $scope.airports=[];
    $scope.airportForm={
        idAirport: 1,
        nameAirport:"",
        airportInTheCity:"2"
    };
    -_refreshAirportsData()
    function _refreshAirportsData() {
        $http({
            method: 'GET',
            url: '/airports'
        }).then(
            function (res) { // success
                $scope.airports = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
    $scope.submitAirport = function() {
        var method="";
        var url="";if($scope.airportForm ==-1){
            method("POST")
            url='/airports'
        }else {
            method = "PUT";
            url = '/airports';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.airportForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createFlight = function() {
        _clearFormData();
    };
    $scope.deleteFlight = function(airport) {
        $http({
            method: 'DELETE',
            url: '/airports' + airport.idAirport
        }).then(_success, _error);
    };
    $scope.editFlight = function(airport) {
        $scope.airportForm.idAirport= airport.idAirport;
        $scope.airportForm.nameAirport = airport.nameAirport;
        $scope.airportForm.airportInTheCity= airport.airportInTheCity;

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


})
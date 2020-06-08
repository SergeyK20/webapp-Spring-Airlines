var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http) {
    $scope.flights = [];
    $scope.userFlight = {
        idUser: "1",
        idFlight: "s12324",
        payment: true
    };
    MainData()
    function MainData() {
        $http({
            method: 'GET',
            url: '/'
        }).then(
            function (res) { // success
                $scope.flights = res.data.flights;
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
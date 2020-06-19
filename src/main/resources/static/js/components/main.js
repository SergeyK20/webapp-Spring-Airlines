var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http,MainService,$route) {
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

    $scope.flights = [];
    $scope.user_flights=[];
    $scope.users_flights=[];
    $scope.user_flight={
        id:1,
        flight:"",
        payment:true,
    }
    $scope.flight = {
        id: "1",
        numFlight: "s12324",
        airportDeparture: airport1,
        airportArrival: airport2,
        departureDate: "03.02.2020",
        departureTime: "17:50",
        aircraft: aircraft,
        price: "5750"
    };
    FlightsData()
    MainData()
    MainsData()
    function MainData() {
        $http({
            method: 'GET',
            url: '/user_flight'
        }).then(
            function (res) { // success
                $scope.user_flights = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };
    function MainsData() {
        $http({
            method: 'GET',
            url: '/user_flight/all'
        }).then(
            function (res) { // success
                $scope.users_flights = res.data;
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
    $scope.addBooking = function (index) {
        if ($scope.flight != null ) {
           MainService.addBooking(index)
               .then(MainData)
                .then (function success(response){
                        $scope.message = 'Place added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding place!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    };
    $scope.addPayment = function (index) {
        MainService.addPayment(index)
            .then(MainData)
            .then(MainsData)
    }
    $scope.deleteFlight = function (index) {
        MainService.deleteFlight(index)
            .then(MainsData)
            .then(MainData)
            .then (function success(response){
                    $scope.message = 'Flight deleted!';
                    $scope.flight = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting Flight!';
                    $scope.message='';
                })
    }

})
app.service('MainService', ['$http', function ($http) {

    this.addBooking = function addBooking(id) {
        return $http({
            method: 'POST',
            url: '/user_flight/booking',
            data:{id:id}
        });
    }
    this.addPayment = function addPayment(id) {
        return $http({
            method: 'PUT',
            url: '/user_flight/payment/'+id,
        });
    }

    this.deleteFlight = function deleteFlight(id) {
        return $http({
            method: 'DELETE',
            url: '/user_flight/remove_booking/' + id
        })
    }


}]);
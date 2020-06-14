var app = angular.module("MainController",[])

app.controller("MainCtrl",function ($scope,$http,MainService) {
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
    $scope.airport=[];
    let city;
    $scope.airport={
        id: 1,
        nameAirport:"Kurumoch",
        airportInTheCity:city
    };
    $scope.flights = [];
    $scope.user_flights=[];
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
        FlightsData();
        setTimeout("location.reload(true);",1)
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

}]);
var app = angular.module('User', []);
app.controller("UserCtrl", function ($scope, $http, UserService) {
    let Up ="ADMIN";
    let Down ="USER";
    $scope.accounts = [];
    $scope.account = {
        idAccount:"1",
        login:"Alex",
        roles:"USER"};
    UserData()

    function UserData() {
        $http({
            method: 'GET',
            url: '/accounts'
        }).then(
            function (res) { // success
                $scope.accounts = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.updateAccess = function (index) {
        UserService.updateAccess(index)
            .then(UserData)
            .then (function success(response){
                    $scope.message = 'update Access!';
                    $scope.account = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error update!';
                    $scope.message='';
                })
    };

    $scope.reducingAccess = function (index) {
        UserService.reducingAccess(index)
            .then(UserData)
            .then (function success(response){
                    $scope.message = 'update Access!';
                    $scope.account = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error update!';
                    $scope.message='';
                })
    };

});
app.service('UserService', ['$http', function ($http) {


    this.updateAccess = function updateAccess(id) {
        return $http({
            method: 'PUT',
            url: '/accounts/updateAccess/' + id,
        })
    }
    this.reducingAccess = function reducingAccess(id) {
        return $http({
            method: 'PUT',
            url: '/accounts/reducingAccess/' + id,
        })
    }
}]);
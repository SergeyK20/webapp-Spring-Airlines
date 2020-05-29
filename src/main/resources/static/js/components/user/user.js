var user = angular.module('user', []);
user.controller("UserCtrl", function ($scope, $http, UserService) {
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


    $scope.updateUser = function () {
        UserService.updateUser($scope.account.idAccount)
            .then(function success(response) {
                    $scope.message = 'User data updated!';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.errorMessage = 'Error updating user!';
                    $scope.message = '';
                });

    }

    $scope.getUser = function () {
        var idAccount = $scope.account.idAccount;
        UserService.getUser($scope.account.idAccount)
            .then(function success(response) {
                    $scope.account = response.data;
                    $scope.account.idAccount = idAccount;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error(response) {
                    $scope.message = '';
                    if (response.status === 404) {
                        $scope.errorMessage = 'User not found!';
                    } else {
                        $scope.errorMessage = "Error getting user!";
                    }
                });
        UserData()
    }


});
user.service('UserService', ['$http', function ($http) {

    this.getUser = function getUser(idAccount) {
        return $http({
            method: 'GET',
            url: '/accounts/' + idAccount
        });
    }


    this.updateUser = function updateUser(idAccount) {
        return $http({
            method: 'PUT',
            url: '/accounts/' + idAccount,
            data: {
                id:id,
            }
        })
    }
}]);
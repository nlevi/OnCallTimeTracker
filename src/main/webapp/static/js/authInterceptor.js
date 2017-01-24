/**
 * Created by levin1 on 1/20/2017.
 */
angular.module('onCallTimeTracker', [ 'ngRoute' ])
    .controller('navigation', function ($rootScope, $scope, $http, $location) {
        var authenticate = function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
        }

        $http.get('user', {headers: headers}).success(function (data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
        });
    })
authenticate();
$scope.credentials = {};
$scope.login = function () {
    authenticate($scope)
}

)

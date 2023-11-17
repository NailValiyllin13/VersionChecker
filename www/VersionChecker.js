var exec = require('cordova/exec');

exports.VersionCheck = function (urlGoogle, classGoogle, urlRuStore, classRuStore, success, error) {
    exec(success, error, 'VersionChecker', 'VersionCheck', [urlGoogle,classGoogle,urlRuStore,classRuStore]);
};

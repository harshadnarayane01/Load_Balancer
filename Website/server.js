var express = require('express');
var app = express();
var sys = require('sys')
var exec = require('child_process').exec;
var ping = require ("ping");


app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers',
  "Origin, X-Requested-With, Content-Type, Accept, Authorization");
  if (req.method === 'OPTIONS' ) {
    res.header('Access-Control-Allow-Methods','PUT,POST,PATCH,DELETE,GET');
    return res.status(200).json({});
  }
  next();
});

app.get('/', function(request, response){
    response.sendFile(__dirname + '/index.html');
});


app.get('/bulbgreen', function(request, response){
    response.sendFile(__dirname + '/green.jpg');
});

app.get('/bulbblack', function(request, response){
    response.sendFile(__dirname + '/black.png');
});

app.get('/bulbred', function(request, response){
    response.sendFile(__dirname + '/red.jpeg');
});


app.get('/bulbyellow', function(request, response){
    response.sendFile(__dirname + '/yellow.jpeg');
});

app.get('/checkVMIStatus', function(request, response) {
  checkVMIStatus(response);
});

app.get('/checkVMIIStatus', function(request, response) {
  checkVMIIStatus(response);
});

function checkVMIStatus (response) {
    var hosts = '172.17.0.2'; // IP of VM I machine can be given
    ping.sys.probe( hosts, function(isAlive) {
      var msg;
      if (isAlive ) {
        msg = "True";
      }
      else {
        msg = "False";
      }
      response.send(msg);
    });
}

function checkVMIIStatus (response) {
    var hosts = '172.17.0.3'; // IP of VMII can be given
    ping.sys.probe( hosts, function(isAlive) {
      var msg;
      if (isAlive ) {
        msg = "True";
      }
      else {
        msg = "False";
      }
      response.send(msg);
    });
}
app.listen(3000, function(){
    console.log('Server running at port 3000: http://127.0.0.1:3000');
});

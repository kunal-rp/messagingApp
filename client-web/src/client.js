const {LoginRequest} = require('./generated/proto/login_pb');
const {LoginClient} = require('./generated/proto/login_grpc_web_pb');

const {SampleRequest} = require('./generated/proto/sample_pb');
const {SampleClient} = require('./generated/proto/sample_grpc_web_pb');

var loginService = new LoginClient('http://localhost:8080',null,null);
var sampleService = new SampleClient('http://localhost:8080',null,null);


export function loginWithToken(token, callback){

	var request = new LoginRequest();
	request.setGoogleToken(token);

	loginService.login(request, {}, function(err, response) {
	  if(err){
	  	console.log(err)
	  	return
	  }
	  callback(response.getJwtToken())
	});
}

export function sampleCall(token){

	var request = new SampleRequest();

	sampleService.sampleCall(request, {'JWTTOKEN':token}, function(err, response) {
	   if(err){
	  	console.log(err)
	  	return
	  }
	  console.log(response.getUserdetails().getUserId())
	});
}
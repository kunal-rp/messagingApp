/**
 * @fileoverview gRPC-Web generated client stub for login
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.login = require('./login_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.login.LoginClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.login.LoginPromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.login.LoginRequest,
 *   !proto.login.LoginResponse>}
 */
const methodDescriptor_Login_login = new grpc.web.MethodDescriptor(
  '/login.Login/login',
  grpc.web.MethodType.UNARY,
  proto.login.LoginRequest,
  proto.login.LoginResponse,
  /**
   * @param {!proto.login.LoginRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.login.LoginResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.login.LoginRequest,
 *   !proto.login.LoginResponse>}
 */
const methodInfo_Login_login = new grpc.web.AbstractClientBase.MethodInfo(
  proto.login.LoginResponse,
  /**
   * @param {!proto.login.LoginRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.login.LoginResponse.deserializeBinary
);


/**
 * @param {!proto.login.LoginRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.login.LoginResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.login.LoginResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.login.LoginClient.prototype.login =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/login.Login/login',
      request,
      metadata || {},
      methodDescriptor_Login_login,
      callback);
};


/**
 * @param {!proto.login.LoginRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.login.LoginResponse>}
 *     A native promise that resolves to the response
 */
proto.login.LoginPromiseClient.prototype.login =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/login.Login/login',
      request,
      metadata || {},
      methodDescriptor_Login_login);
};


module.exports = proto.login;


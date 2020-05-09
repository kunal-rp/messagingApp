/**
 * @fileoverview gRPC-Web generated client stub for sample
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.sample = require('./sample_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.sample.SampleClient =
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
proto.sample.SamplePromiseClient =
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
 *   !proto.sample.SampleRequest,
 *   !proto.sample.SampleResponse>}
 */
const methodDescriptor_Sample_sampleCall = new grpc.web.MethodDescriptor(
  '/sample.Sample/sampleCall',
  grpc.web.MethodType.UNARY,
  proto.sample.SampleRequest,
  proto.sample.SampleResponse,
  /**
   * @param {!proto.sample.SampleRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.sample.SampleResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.sample.SampleRequest,
 *   !proto.sample.SampleResponse>}
 */
const methodInfo_Sample_sampleCall = new grpc.web.AbstractClientBase.MethodInfo(
  proto.sample.SampleResponse,
  /**
   * @param {!proto.sample.SampleRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.sample.SampleResponse.deserializeBinary
);


/**
 * @param {!proto.sample.SampleRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.sample.SampleResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.sample.SampleResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.sample.SampleClient.prototype.sampleCall =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/sample.Sample/sampleCall',
      request,
      metadata || {},
      methodDescriptor_Sample_sampleCall,
      callback);
};


/**
 * @param {!proto.sample.SampleRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.sample.SampleResponse>}
 *     A native promise that resolves to the response
 */
proto.sample.SamplePromiseClient.prototype.sampleCall =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/sample.Sample/sampleCall',
      request,
      metadata || {},
      methodDescriptor_Sample_sampleCall);
};


module.exports = proto.sample;


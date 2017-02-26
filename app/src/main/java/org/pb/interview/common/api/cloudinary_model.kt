package org.pb.interview.common.api


class ImageListQueryResponse(var resources: List<Resource>? = null,
                             var updated_at: String? = null)
class Resource(var publicId: String? = null,
               var version: Int? = null,
               var format: String? = null,
               var width: Int? = null,
               var height: Int? = null,
               var type:String? = null,
               var created_at:String? = null,
               var context: Context? = null)

class Context(var custom:Custom? = null)
class Custom(var main:String? = null,
             var caption:String? = null)
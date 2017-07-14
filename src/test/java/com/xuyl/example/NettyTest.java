package com.xuyl.example;

import com.xuyl.example.main.NettyContainer;
import org.jboss.resteasy.spi.Registry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * NettyTest
 * Created by xuyl on 2016/9/26 16:00.
 */
public class NettyTest {

    public static void main(String[] args) throws Exception {
        Registry registry = NettyContainer.start().getRegistry();
        registry.addPerRequestResource(Resource.class);
    }

    /*
     * @Path，标注资源类或方法的相对路径
     * @GET，@PUT，@POST，@DELETE，标注方法是用的HTTP请求的类型
     * @Produces，标注返回的MIME媒体类型
     * @Consumes，标注可接受请求的MIME媒体类型
     * @PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam,分别标注方法的参数来自于HTTP请求的不同位置，
     * 例如 @PathParam来自于URL的路径，
     *      @QueryParam来自于URL的查询参数，
     *      @HeaderParam来自于HTTP请求的头信息，
     *      @CookieParam来自于HTTP请求的Cookie。
     * */

    @Path("/")
    public static class Resource {
        @GET
        @Path("/test")
        @Produces("application/json") //"application/json;charset=utf-8"
        public Response hello() {
            TestModel model = new TestModel();
            model.setId("test-id-00001");
            model.setDate(new Date());
            return Response.status(200).entity(model).build();
        }

        @GET
        @Produces("application/json")
        @Path("subpath/{id}")
        public String get(@PathParam("id")String id){
            String result = "hello"+id;
            return result;
        }


        @GET
        @Path("/index")
        @Produces(MediaType.TEXT_HTML)
        public String index() {
            // 在这可以整合velocity
            String s = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<form action=\"action_page.php\">\n" +
                    "First name:<br>\n" +
                    "<input type=\"text\" name=\"firstname\" value=\"Mickey\">\n" +
                    "<br>\n" +
                    "Last name:<br>\n" +
                    "<input type=\"text\" name=\"lastname\" value=\"Mouse\">\n" +
                    "<br><br>\n" +
                    "<input type=\"submit\" value=\"Submit\">\n" +
                    "</form> \n" +
                    "\n" +
                    "<p>If you click \"Submit\", the form-data will be sent to a page called \"action_page.php\".</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
            return s;
        }



        @GET
        @Path("empty")
        public void empty() {

        }

        @GET
        @Path("/exception")
        @Produces("text/plain")
        public String exception() {
            throw new RuntimeException();
        }
    }

    static class TestModel {
        private String id;

        private Date date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}

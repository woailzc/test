package com.example.okhttptest;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;



import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends Activity implements android.view.View.OnClickListener {
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.get);
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
        System.out.println("res");

        new Thread(new Runnable() {
            @Override
            public void run() {
                //1. 得到OKHttpClient对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //2. 获取Request对象 ,内部也是通过建造者模式去封装的一些请求参数
                String url = "http://172.16.160.58:8080/okhttp_struts2_get/login.action?password=123&&username=123";
                Request request = new Request.Builder().url(url).build();
                //3. 获取Call对象
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
           
                    
		
					@Override
					public void onFailure(Call arg0, IOException e) {
						 Log.e("onFailure:",e.getMessage());
	                        e.printStackTrace();
						
					}
					@Override
					public void onResponse(Call arg0, Response response ) throws IOException {
						final String res =response.body().string();
                        Log.e("onRespose1",res);
                        
                        runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO 自动生成的方法存根
								
								Toast.makeText(MainActivity.this,res, Toast.LENGTH_SHORT).show();

							}
						});
						
					}
				
                });
                //4. 获取Response对象
//                try {
//                    Response response = call.execute();
//                    //响应体
//                    ResponseBody body = response.body();
//                    final String result = body.string();  //注意这里是string()方法,不要写成toString()
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (IOException e) {	
//                    e.printStackTrace();
//                }
            }
        }).start();
        
		//post方法
//        new  Thread(new Runnable() {
////          @Override
//          public void run() {
//             // String url = "http://172.16.160.90:7001/TestOkHttp/servlet/TestPost";
//             // http://127.0.0.1:7001/test01/TestDynamicQuery
//        	  //String url = "http://172.16.160.58:8080/okhttp_struts2_get/login.action";
//              String url = "http://172.16.160.58:8080/okhttp_struts2_get/loginPost.action";
//             
//              OkHttpClient client=new OkHttpClient();
//              FormBody body=new FormBody.Builder()
//                      .add("username","梁智程")
//                      .add("password","22")
//                      .build();
//              final Request request=new Request.Builder().post(body).url(url).build();
//              Call call=client.newCall(request);
//              call.enqueue(new Callback() {
//                  @Override
//                  public void onFailure(Call call, IOException e) {
//                      Log.e("onFailie:",e.toString());
//                  }
//
//                  @Override
//                  public void onResponse(Call call, Response response) throws IOException {
//                      Log.e("onResponse:",response.body().string());
//                  }
//              });
//
//
//          }
//      }).start();
//		Toast.makeText(MainActivity.this,"我在这", Toast.LENGTH_SHORT).show();
	}

	
//    public void testpost(View view) {
//        new  Thread(new Runnable() {
////            @Override
//            public void run() {
//               // String url = "http://172.16.160.90:7001/TestOkHttp/servlet/TestPost";
//               // http://127.0.0.1:7001/test01/TestDynamicQuery
//                String url = " http://127.0.0.1:7001/test01/TestDynamicQuery";
//                OkHttpClient client=new OkHttpClient();
//                FormBody body=new FormBody.Builder()
//                        .add("name","崔世宙")
//                        .add("age","22")
//                        .build();
//                final Request request=new Request.Builder().post(body).url(url).build();
//                Call call=client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e("onFailie:",e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.e("onResponse:",response.body().string());
//                    }
//                });
//
//
//            }
//        }).start();
    
//    public void testjson(View view){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // String url = "http://172.16.160.90:7001/TestOkHttp/servlet/TestPost";
//                // http://127.0.0.1:7001/test01/TestDynamicQuery
//                String url = " http://172.16.160.90:7001/test01/TestUserQuery";
//                Gson gson=new Gson();
//                User user=new User("Joe","9695");
//                String json=gson.toJson(user);
//
//                OkHttpClient client=new OkHttpClient();
//                RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),json);
//                final Request request = new Request.Builder().post(body).url(url).build();
//                Call call=client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e("onFailie:",e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Gson gson1 = new Gson();
//                        java.lang.reflect.Type type = new TypeToken<User>() {}.getType();
//                       // User user = gson1.fromJson(response.body().string(), type);
//                       // Log.e("onResponse:",user.getUsername());
//                        JsonParser parser=new JsonParser();
//                        JsonArray jsonArray=parser.parse(response.body().string()).getAsJsonArray();
//                        //ArrayList<User> arrayList=new ArrayList<User>();
//                        for(JsonElement obj:jsonArray){
//                            User user1=gson1.fromJson(obj,User.class);
//                            Log.e("TagUser---","用户名："+user1.getUsername()+"密码:"+user1.getUserpassword());
//                        }
//
//                        //Log.e("tadsdf",response.body().string());
//                       //  Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        }).start();
    }
    








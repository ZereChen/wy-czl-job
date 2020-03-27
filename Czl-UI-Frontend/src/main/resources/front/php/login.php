
<?php
/**
 * Created by PhpStorm.
 * User: yyz10
 * Date: 2018/7/29
 * Time: 8:33
 */
header("Content-Type:text/html;charset=utf-8");
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    $Uname = $_REQUEST["Uname"];
    $Upwd = $_REQUEST["Upwd"];
//    echo "Uname : ".$Uname;
//    echo "Upwd : ".$Upwd;


    $raw = array('status' => 0, 'msg' => '初始化');
//    用户账号验证
//    数据库链接

    $con = mysqli_connect("127.0.0.1:3306","root","","wwyx");

    if (!$con) {
        $raw = array('status' => 0, 'msg' => '数据库链接错误');
        $result = json_encode($raw);
        echo  $result;
        mysqli_close($con);
        die('Could not connect: ' . mysqli_connect_error());
    }
    mysqli_select_db($con,"wwyx");
    $sql = "SELECT * FROM user_account where Uname = '".$Uname."' and Upwd = '".$Upwd."'";
//    echo  $sql;

//    $sql_result的值为true或false

    $sql_result = mysqli_query($con, $sql);

//    如果没有数据，mysqli_fetch_array返回false

    if (mysqli_fetch_array($sql_result, MYSQLI_ASSOC)){
        $raw = array('status' => 1, 'msg' => '登录成功');
        mysqli_free_result($sql_result);
    }else{
        $raw = array('status' => 0, 'msg' => '登录失败');
    }
    $result = json_encode($raw);
    echo  $result;
    mysqli_close($con);
}else{
    echo json_encode(array('status' => -1, 'msg' => '未知的提交'));
}

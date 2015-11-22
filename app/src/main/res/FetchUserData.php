<php>
    $con = mysql_connect("mysql2.000webhost.com", "a9686140_testdb", "testing123", "a9686140_testdb");

    $uname = $__POST["uname"];
    $pass = $__POST["pass"];

    $statement = mysqli_prepare($con, "select * from User where uname = ? and pass = ?;");
    mysqli_stmt_bind_param($statement, "ss", $uname, $pass);
    mysqli_execute($statement);

    //try it with $temp too
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $uname, $uname, $pass, $age);

    $user = array();

    while(mysqli_stmt_fetch($statement))
    {
        $user[name] = $name;
        $user[uname]= $uname;
        $user[pass] = $pass;
        $user[age]= $age;
    }

    echo json_encode($user);

    mysqli_stmt_close($statement);

    mysqli_close($con);


</php>
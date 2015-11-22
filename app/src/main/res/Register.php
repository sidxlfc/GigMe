<php>
    $con = mysql_connect("mysql2.000webhost.com", "a9686140_testdb", "testing123", "a9686140_testdb");

    $name = $_POST["name"];
    $uname = $__POST["uname"];
    $pass = $__POST["pass"];
    $age = $_POST["age"];

    $statement = mysqli_prepare($con, "insert into User (name, uname, pass, age) values (?, ?, ?, ?);");

    mysqli_stmt_bind_param($statement, "sssi", $name, $uname, $pass, $age);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);

    mysqli_close($con);

</php>

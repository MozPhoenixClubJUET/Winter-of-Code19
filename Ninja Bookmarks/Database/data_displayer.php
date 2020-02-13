
<html>
    <head>
    <link  rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Fugaz+One|Monoton&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Zhi+Mang+Xing&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Abel&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Press+Start+2P&display=swap" rel="stylesheet">
    </head>
    <body >
       <div class ="logo"> <img src="logo2.png" height ="95" width="100" align="middle"/>
      <span class ="text"><span id="h">H</span>ere Is What Ninja Stored For You...</span>
</div><br><br><br><br><br> <br><br> <br><br> 
 
   <div class= "a">
    
  <form  method="POST" action="search.php">
  <input type="text" placeholder="Search....." name="text" id="text" autocomplete="off">
  <input type="submit" value="Search"  id="sbut" name="go">
  <a href="data.json" id="export" download>
      Export 
  </a>
  </form>
  </div>
<br>
    

       <div class= "display">
       <br><br>
       <?php
$db='id12251806_bookmarkext';
$server ='localhost';
$username = 'id12251806_localhost';
$password='golugolu';
$conn = new mysqli($server,$username,$password,$db);

$fh = fopen('data.json', 'w');
//mysql_connect('host', 'username', 'password');
$sql="SELECT * FROM `bookmarks`";
$res=mysqli_query($conn,$sql);
while ($row = mysqli_fetch_array($res)) {
    $last = end($row);
    foreach ($row as $item) {
        fwrite($fh, $item);
        if ($item != $last)
            fwrite($fh, "\t");
    }
    fwrite($fh, "\n");
}
fclose($fh);




$sql="SELECT title,url,note FROM bookmarks";
$res=mysqli_query($conn,$sql);
if(mysqli_num_rows($res)>0){
    while($row=mysqli_fetch_assoc($res)){
      echo "<div class ='show'>"."<div class ='title'> "."".$row['title']."</div>"."<div class ='url'>"
        . "<a href= '".$row['url']."' target='_blank'>"."<div class ='visit'>Visit Site</div>"."</a></div>"."<div class ='note'>"."<span id='note_title'>Note</span><br><span class ='note_display'> ".$row['note']."</span></div>"."</div>"."<br><br><br>";
    }
}

?>

       </div>


        
    </body>
    </html>
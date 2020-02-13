<?php
$url = $_POST['link'];
$title=$_POST['title'];
$note =$_POST['note'];
$db='id12251806_bookmarkext';
$server ='localhost';
$username = 'id12251806_localhost';
$password='golugolu';
$conn = new mysqli($server,$username,$password,$db);
$sql="INSERT INTO bookmarks (url, title,note ) VALUES  ('$url','$title','$note')";
if($conn->query($sql)===TRUE){
    echo "done ";
}
$server ='localhost';
$db="internal_ass_boomark";
$username='roo_folder_123_for_unique_id';



?>

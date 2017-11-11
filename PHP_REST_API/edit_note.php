<?php
//Adds a TODO to TODO List JSON
//echo "started";
$noteJSON = json_decode($_POST['note'],true);

$noteID = $_POST['note_id'];


$filename = "notes.json";

$response = array();

$content = file_get_contents($filename);
//var_dump($content);
$jsonArray = json_decode($content,true);

for($i=0;$i<count($jsonArray);$i++) {
	if($jsonArray[$i]["id"]==$noteID) {
		$noteJSON["id"] = $noteID;
		$jsonArray[$i] = $noteJSON;
	}
}

//$finalData = array_push($jsonArray, $noteJSON);
//var_dump($finalData);
$finalJson= json_encode($jsonArray);
$status = file_put_contents($filename,$finalJson);
if(status!=FALSE) {
	$response["error_code"] = 0;
	$response["error_message"] = "";
} else {
	$response["error_code"] = 1;
	$response["error_message"] = "Could not save data";
}

echo json_encode($response);

?>
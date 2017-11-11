<?php
//Adds a TODO to TODO List JSON
//echo "started";
$noteJSON = json_decode($_REQUEST['new_note'],true);
//var_dump($noteJSON);
$filename = "notes.json";

$response = array();

$content = file_get_contents($filename);
//var_dump($content);
$jsonArray = json_decode($content,true);
//var_dump($jsonArray);


$id = 0;
foreach($jsonArray as $json) {
	if($id<=$json["id"]) {
		$id = $json["id"]+1;
	}
//var_dump($json);
}
$noteJSON["id"] = $id;
$finalData = array_push($jsonArray, $noteJSON);
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
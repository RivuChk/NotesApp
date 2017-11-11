<?php
	$filename = "notes.json";
	$response["error_code"] = 0;
	$response["error_message"] = "";
	$content = file_get_contents($filename);
	//var_dump($content);
	$jsonArray = json_decode($content,true);
	$response["data"]=$jsonArray;
	echo json_encode($response);
?>
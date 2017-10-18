<?php 
foreach(glob("../model/*.php") as $file){
    require_once $file;
}

require_once '../controller/Controller.php';

$urlms = new URLMS();
$controller = new Controller($urlms);
?>

<html>
	<head>
	<title>URLMS</title>
	</head>

	<body>
		<?php
			if (isset($_GET['email']) && isset($_GET['password'])) {
			    if ($controller->login($_GET['email'], $_GET['password'])) {
		?>
				Hi <?php echo $_GET['email']; ?>.
		<?php
				} else {
		?>
				Wrong password for <?php echo $_GET['email']; ?>.
		<?php
				}
			}

			else{
		?>
		<form method="GET">
			Email:<input type="text" name="email" placeholder="Email"/> <br/>
			Password:<input type="password" name="password"/> <br/> <br/>
			<input type="submit" name="Login"/>
		</form>
		<?php
				}
		?>
	</body>
</html>
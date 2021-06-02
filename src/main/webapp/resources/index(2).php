<!DOCTYPE html>
<html lang="en-us">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Unity WebGL Player | PruebaCompatibilidadUnity</title>
    <link rel="shortcut icon" href="TemplateData/favicon.ico">
    <link rel="stylesheet" href="TemplateData/style.css">
    <script src="TemplateData/UnityProgress.js"></script>
    <script src="Build/UnityLoader.js"></script>
    <script>
      var unityInstance = UnityLoader.instantiate("unityContainer", "Build/WebGL.json", {onProgress: UnityProgress});
    </script>
  </head>
  <body>
    <div class="webgl-content">
      <div id="unityContainer" style="width: 400px; height: 400px"></div>
      <div class="footer">
        <div class="webgl-logo"></div>
        <div class="fullscreen" onclick="unityInstance.SetFullscreen(1)"></div>
        <div class="title">PruebaCompatibilidadUnity</div>
      </div>
    </div>
    <div style="border: solid 1px black; width: 200px; height: 30px; margin: auto; text-align: center;" onclick="unityInstance.SendMessage('mutant@Standing Greeting', 'Saludar')">Saludar</div>
    <div style="border: solid 1px black; width: 200px; height: 30px; margin: auto; text-align: center;" onclick="unityInstance.SendMessage('mutant@Standing Greeting', 'Bailar')">Bailar</div>
  </body>
</html>

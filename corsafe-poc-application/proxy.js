const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const app = express();

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
  

  if (req.method === 'OPTIONS') {
    return res.status(200).json({});
  }

  next();
});

app.use(
  '/api', 
  createProxyMiddleware({
<<<<<<< HEAD

    target: 'https://jfk-1.connectors.camunda.io/', 

=======
    target: 'https://jfk-1.connectors.camunda.io/', 
>>>>>>> 2b5b62dabd3750227f4e6d62f4365297ad24b860
    changeOrigin: true,
    pathRewrite: {
      '^/api': '',
    },
  })
);

app.listen(5000, () => {
  console.log('Proxy server is running on http://localhost:5000');
});

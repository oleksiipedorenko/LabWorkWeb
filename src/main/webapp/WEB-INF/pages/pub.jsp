<%--
  Created by IntelliJ IDEA.
  User: oleksii
  Date: 11/14/15
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Pub</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/my-style.css">
</head>
<body>
<div class="container-fluid">
    <div class="page-header text-centred">
        <h1>Java Hand Made Pub</h1>
    </div>
    <div class="row">
        <div class="col-lg-offset-1 col-lg-10">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#" id="order-tab">Make an order</a></li>
                <li role="presentation"><a href="#" id="status-tab">Current beer status</a></li>
                <li role="presentation"><a href="#" id="contact-tab">Contact information</a></li>
            </ul>
        </div>
    </div>

    <div class="top-buffer tab-panel" id="order-panel">

        <div class="row top-buffer">
            <div class="col-lg-offset-3 col-lg-6">
                <div class="form-inline">
                    <div class="form-group">
                        <label for="kindSelect">Select kind</label>
                        <select class="form-control" id="kindSelect"></select>
                    </div>
                    <div class="form-group">
                        <label for="count">Select glasses count</label>
                        <input type="text" class="form-control" id="count" placeholder="glasses count">
                    </div>
                    <button class="btn btn-default" id="submit-order">Send order</button>
                </div>
            </div>
        </div>
        <div class="row top-buffer">
            <div class="col-lg-offset-3 col-lg-6">
                <p id="client-order-response" class="text-centred"></p>
            </div>
        </div>

    </div>
    <div class="top-buffer tab-panel hidden" id="status-panel">
        <div class="row top-buffer">
            <div class="col-lg-offset-4 col-lg-4">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Kind</th>
                            <th>Glasses left</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-offset-6 col-lg-3">
                <div class="form-inline">
                    <button class="btn btn-default" id="refresh-status">Refresh</button>
                    <button class="btn btn-default" data-toggle="modal" data-target="#requestModal">
                        Request glasses
                    </button>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="requestModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Please, fill in glasses request</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form">
                                    <div class="form-group">
                                        <label for="lightGlasses">Glasses of light beer</label>
                                        <input type="text" class="form-control" id="lightGlasses"
                                               placeholder="Light beer amount">
                                    </div>
                                    <div class="form-group">
                                        <label for="darkGlasses">Glasses of dark beer</label>
                                        <input type="text" class="form-control" id="darkGlasses"
                                               placeholder="Dark beer amount">
                                    </div>
                                    <div class="form-group">
                                        <label for="aleGlasses">Glasses of ale</label>
                                        <input type="text" class="form-control" id="aleGlasses"
                                               placeholder="Ale amount">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="refill-bar">Request</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="top-buffer tab-panel hidden" id="contact-panel">
        <div class="row top-buffer">
            <div class="panel panel-default col-lg-offset-3 col-lg-6">

                <div class="panel-heading">You can find us following next information</div>

                <ul class="list-group">
                    <li class="list-group-item">Country :</li>
                    <li class="list-group-item">City : </li>
                    <li class="list-group-item">Address: </li>
                    <li class="list-group-item">Phone: </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/my-script.js"></script>
</body>
</html>

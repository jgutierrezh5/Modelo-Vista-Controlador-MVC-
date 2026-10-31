<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema MVC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${ctx}/">Sistema MVC</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link ${moduloActivo == 'inicio' ? 'active' : ''}" href="${ctx}/">Inicio</a></li>
                <li class="nav-item"><a class="nav-link ${moduloActivo == 'marcas' ? 'active' : ''}" href="${ctx}/marcas">Marcas</a></li>
                <li class="nav-item"><a class="nav-link ${moduloActivo == 'productos' ? 'active' : ''}" href="${ctx}/productos">Productos</a></li>
                <li class="nav-item"><a class="nav-link ${moduloActivo == 'clientes' ? 'active' : ''}" href="${ctx}/clientes">Clientes</a></li>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4">

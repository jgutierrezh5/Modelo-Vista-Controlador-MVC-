<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="moduloActivo" value="marcas" />
<%@ include file="/WEB-INF/vista/layout/header.jsp" %>

<div class="mb-4">
    <h2 class="fw-bold">Marcas</h2>
    <p class="text-secondary mb-0">Registro y visualización de marcas.</p>
</div>

<c:if test="${not empty param.error}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <c:choose>
            <c:when test="${param.error == 'marca_en_uso'}">No se puede eliminar la marca porque tiene productos asociados.</c:when>
            <c:otherwise>Revise los datos: el nombre y el país son obligatorios.</c:otherwise>
        </c:choose>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
    </div>
</c:if>

<div class="row g-4">
    <div class="col-lg-4">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h5 class="card-title mb-3">Nueva marca</h5>
                <form method="post" action="${ctx}/marcas">
                    <input type="hidden" name="accion" value="guardar">
                    <div class="mb-3">
                        <label class="form-label" for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" maxlength="100" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="pais">País</label>
                        <input type="text" id="pais" name="pais" class="form-control" maxlength="80" required>
                    </div>
                    <button type="submit" class="btn btn-dark w-100">Guardar</button>
                </form>
            </div>
        </div>
    </div>

    <div class="col-lg-8">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h5 class="card-title mb-3">Listado de marcas</h5>
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>País</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="marca" items="${marcas}">
                            <tr>
                                <td>${marca.id}</td>
                                <td><c:out value="${marca.nombre}" /></td>
                                <td><c:out value="${marca.pais}" /></td>
                                <td>
                                    <form method="post" action="${ctx}/marcas" class="d-inline"
                                          onsubmit="return confirm('¿Desea eliminar esta marca?')">
                                        <input type="hidden" name="accion" value="eliminar">
                                        <input type="hidden" name="id" value="${marca.id}">
                                        <button type="submit" class="btn btn-sm btn-outline-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty marcas}">
                            <tr><td colspan="4" class="text-center text-secondary">Aún no hay marcas. Registre la primera con el formulario.</td></tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/vista/layout/footer.jsp" %>

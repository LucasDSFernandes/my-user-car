package com.lucasdsf.api.user.rest.resouces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lucasdsf.api.user.domains.model.VehicleDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface VehicleReources {
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para criar uma conta usuario")
	ResponseEntity<VehicleDto> createVehicle(@RequestHeader("Authorization") String authorization, @RequestBody VehicleDto vehicle);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para obter a informação do veiculo selecionado")
	ResponseEntity<VehicleDto> getInfoCar(@RequestHeader("Authorization") String authorization,  Long id);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para atualizar veiculo")
	ResponseEntity<VehicleDto> updateVehicle(@RequestHeader("Authorization") String authorization, Long id, VehicleDto vehicleDto);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para deletar veiculos")
	ResponseEntity<VehicleDto> deleteVehicle(@RequestHeader("Authorization") String authorization, Long id);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para listar todos os veiculos")
	ResponseEntity<List<VehicleDto>> listAll(@RequestHeader("Authorization") String authorization);





}

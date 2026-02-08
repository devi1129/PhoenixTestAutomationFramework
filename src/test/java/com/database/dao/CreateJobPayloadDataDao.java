package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.database.DatabaseManager;
import com.dataproviders.api.Bean.CreateJobBean;

public class CreateJobPayloadDataDao {

	private static final String SQL_QUERY =

			"""
					  SELECT
					  j.mst_service_location_id,
					  j.mst_platform_id,
					  j.mst_warrenty_status_id,
					  j.mst_oem_id,

					  c.id,
					  c.first_name,
					  c.last_name,
					  c.mobile_number,
					  c.mobile_number_alt,
					  c.email_id,
					  c.email_id_alt,

					  a.flat_number,
					  a.apartment_name,
					  a.street_name,
					  a.landmark,
					  a.area,
					  a.pincode,
					  a.country,
					  a.state,

					  p.serial_number,
					  p.imei1,
					  p.imei2,
					  p.popurl,
					  p.dop,
					  p.mst_model_id,
					 prob.remark,
					prob.mst_problem_id
					FROM tr_customer c
					INNER JOIN tr_customer_address a
					  ON c.tr_customer_address_id = a.id
					INNER JOIN tr_customer_product p
					  ON c.id = p.tr_customer_id
					INNER JOIN tr_job_head j
					  ON c.id = j.tr_customer_id
					INNER JOIN map_job_problem as prob
					on j.id=prob.tr_job_head_id
					LIMIT 5;
								""";

	public static Iterator<CreateJobBean> getCreateJobPayLoadData() {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		List<CreateJobBean> beanList=new ArrayList<CreateJobBean>();
		
		CreateJobBean bean=new CreateJobBean();
		try {
			connection = DatabaseManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(SQL_QUERY);

			while(resultSet.next())
			{
				  // tr_job_head (j)
			    bean.setMst_service_location_id(resultSet.getString("mst_service_location_id"));
			    bean.setMst_platform_id(resultSet.getString("mst_platform_id"));
			    bean.setMst_warrenty_status_id(resultSet.getString("mst_warrenty_status_id"));
			    bean.setMst_oem_id("1");

			    // tr_customer (c)
			    bean.setCustomer__first_name(resultSet.getString("first_name"));
			    bean.setCustomer__last_name(resultSet.getString("last_name"));
			    bean.setCustomer__mobile_number(resultSet.getString("mobile_number"));
			    bean.setCustomer__mobile_number_alt(resultSet.getString("mobile_number_alt"));
			    bean.setCustomer__email_id(resultSet.getString("email_id"));
			    bean.setCustomer__email_id_alt(resultSet.getString("email_id_alt"));

			    // tr_customer_address (a)
			    bean.setCustomer_address__flat_number(resultSet.getString("flat_number"));
			    bean.setCustomer_address__apartment_name(resultSet.getString("apartment_name"));
			    bean.setCustomer_address__street_name(resultSet.getString("street_name"));
			    bean.setCustomer_address__landmark(resultSet.getString("landmark"));
			    bean.setCustomer_address__area(resultSet.getString("area"));
			    bean.setCustomer_address__pincode(resultSet.getString("pincode"));
			    bean.setCustomer_address__country(resultSet.getString("country"));
			    bean.setCustomer_address__state(resultSet.getString("state"));
			    bean.setCustomer_product__product_id("1");

			    // tr_customer_product (p)
			    bean.setCustomer_product__serial_number(resultSet.getString("serial_number"));
			    bean.setCustomer_product__imei1(resultSet.getString("imei1"));
			    bean.setCustomer_product__imei2(resultSet.getString("imei2"));
			    bean.setCustomer_product__popurl(resultSet.getString("popurl"));
			    bean.setCustomer_product__dop(resultSet.getString("dop"));
			    bean.setCustomer_product__mst_model_id("1");

			    // map_job_problem (prob)
			    bean.setProblems__id(resultSet.getString("mst_problem_id"));
			 
			    bean.setProblems__remark(resultSet.getString("remark"));
			    
			    beanList.add(bean);

			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return beanList.iterator();

	}

}

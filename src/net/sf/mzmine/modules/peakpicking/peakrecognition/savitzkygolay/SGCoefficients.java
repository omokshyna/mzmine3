/*
 * Copyright 2006-2009 The MZmine 2 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.sf.mzmine.modules.peakpicking.peakrecognition.savitzkygolay;

public final class SGCoefficients {

	public static final double[][] SGCoefficientsFirstDerivative = {
			{ 0.0 },
			{ 0.0, 0.500 },
			{ 0.0, 0.100, 0.200 },
			{ 0.0, 0.036, 0.071, 0.107 },
			{ 0.0, 0.017, 0.033, 0.050, 0.067 },
			{ 0.0, 0.009, 0.018, 0.027, 0.036, 0.045 },
			{ 0.0, 0.005, 0.011, 0.016, 0.022, 0.027, 0.033 },
			{ 0.0, 0.004, 0.007, 0.011, 0.014, 0.018, 0.021, 0.025 },
			{ 0.0, 0.002, 0.005, 0.007, 0.010, 0.012, 0.015, 0.017, 0.020 },
			{ 0.0, 0.002, 0.004, 0.005, 0.007, 0.009, 0.011, 0.012, 0.014,
					0.016 },
			{ 0.0, 0.001, 0.003, 0.004, 0.005, 0.006, 0.008, 0.009, 0.010,
					0.012, 0.013 },
			{ 0.0, 0.001, 0.002, 0.003, 0.004, 0.005, 0.006, 0.007, 0.008,
					0.009, 0.010, 0.011 },
			{ 0.0, 0.001, 0.002, 0.002, 0.003, 0.004, 0.005, 0.005, 0.006,
					0.007, 0.008, 0.008, 0.009 } };

	public static final double[][] SGCoefficientsFirstDerivativeQuartic = {
			{ 0.0 },
			{ 0.0, 0.667 },
			{ 0.0, 0.667, -0.083 },
			{ 0.0, 0.230, 0.266, -0.087 },
			{ 0.0, 0.106, 0.162, 0.120, -0.072 },
			{ 0.0, 0.057, 0.098, 0.103, 0.057, -0.058 },
			{ 0.0, 0.035, 0.062, 0.075, 0.066, 0.027, -0.047 },
			{ 0.0, 0.022, 0.041, 0.053, 0.055, 0.042, 0.012, -0.039 },
			{ 0.0, 0.015, 0.029, 0.039, 0.043, 0.040, 0.028, 0.004, -0.032 },
			{ 0.0, 0.011, 0.021, 0.029, 0.034, 0.034, 0.029, 0.018, 0.000,
					-0.027 },
			{ 0.0, 0.008, 0.016, 0.022, 0.026, 0.028, 0.027, 0.022, 0.012,
					-0.003, -0.023 },
			{ 0.0, 0.006, 0.012, 0.017, 0.021, 0.023, 0.023, 0.021, 0.016,
					0.008, -0.004, -0.020 },
			{ 0.0, 0.006, 0.009, 0.013, 0.017, 0.019, 0.020, 0.019, 0.016,
					0.012, 0.005, -0.005, -0.017 } };

	public static final double[][] SGCoefficientsSecondDerivative = {
			{ 0.0 },
			{ -1.0, 0.5 },
			{ -0.143, -0.071, 0.143 },
			{ -0.048, -0.036, 0.0, 0.060 },
			{ -0.022, -0.018, -0.009, 0.008, 0.030 },
			{ -0.012, -0.010, -0.007, -0.001, 0.007, 0.017 },
			{ -0.007, -0.006, -0.005, -0.002, 0.001, 0.005, 0.011 },
			{ -0.005, -0.004, -0.004, -0.002, -0.001, 0.002, 0.004, 0.007 },
			{ -0.003, -0.003, -0.003, -0.002, -0.001, 0.000, 0.002, 0.003,
					0.005 },
			{ -0.002, -0.002, -0.002, -0.002, -0.001, 0.000, 0.000, 0.001,
					0.003, 0.004 },
			{ -0.002, -0.002, -0.001, -0.001, -0.001, -0.001, 0.000, 0.001,
					0.001, 0.002, 0.003 },
			{ -0.001, -0.001, -0.001, -0.001, -0.001, -0.001, 0.000, 0.000,
					0.001, 0.001, 0.002, 0.002 },
			{ -0.001, -0.001, -0.001, -0.001, -0.001, -0.001, 0.000, 0.000,
					0.000, 0.001, 0.001, 0.001, 0.002 } };

	public static final double[][] SGCoefficientsSecondDerivativeQuartic = {
			{ 0.0 },
			{ -1.250, 0.567 },
			{ -1.250, 0.567, -0.042 },
			{ -0.265, -0.072, 0.254, -0.049 },
			{ -0.108, -0.061, -0.044, 0.108, -0.037 },
			{ -0.055, -0.040, 0.000, 0.043, 0.051, -0.026 },
			{ -0.032, -0.026, -0.008, 0.014, 0.030, 0.025, -0.019 },
			{ -0.021, -0.018, -0.009, 0.003, 0.014, 0.020, 0.013, -0.014 },
			{ -0.014, -0.012, -0.008, -0.001, 0.006, 0.012, 0.013, 0.007,
					-0.011 },
			{ -0.010, -0.009, -0.006, -0.002, 0.002, 0.007, 0.009, 0.009,
					0.004, -0.008 },
			{ -0.007, -0.007, -0.005, -0.003, 0.000, 0.003, 0.006, 0.007,
					0.006, 0.002, -0.006 },
			{ -0.006, -0.005, -0.004, -0.003, -0.001, 0.002, 0.004, 0.005,
					0.005, 0.004, 0.001, -0.005 },
			{ -0.004, -0.004, -0.003, -0.002, -0.001, 0.001, 0.002, 0.003,
					0.004, 0.004, 0.003, 0.000, -0.004 } };

}
